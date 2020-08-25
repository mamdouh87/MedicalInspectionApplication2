import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOphthalmicInspection, OphthalmicInspection } from 'app/shared/model/ophthalmic-inspection.model';
import { OphthalmicInspectionService } from './ophthalmic-inspection.service';
import { OphthalmicInspectionComponent } from './ophthalmic-inspection.component';
import { OphthalmicInspectionDetailComponent } from './ophthalmic-inspection-detail.component';
import { OphthalmicInspectionUpdateComponent } from './ophthalmic-inspection-update.component';

@Injectable({ providedIn: 'root' })
export class OphthalmicInspectionResolve implements Resolve<IOphthalmicInspection> {
  constructor(private service: OphthalmicInspectionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOphthalmicInspection> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ophthalmicInspection: HttpResponse<OphthalmicInspection>) => {
          if (ophthalmicInspection.body) {
            return of(ophthalmicInspection.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OphthalmicInspection());
  }
}

export const ophthalmicInspectionRoute: Routes = [
  {
    path: '',
    component: OphthalmicInspectionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalInspectionApplicationApp.ophthalmicInspection.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OphthalmicInspectionDetailComponent,
    resolve: {
      ophthalmicInspection: OphthalmicInspectionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.ophthalmicInspection.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OphthalmicInspectionUpdateComponent,
    resolve: {
      ophthalmicInspection: OphthalmicInspectionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.ophthalmicInspection.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OphthalmicInspectionUpdateComponent,
    resolve: {
      ophthalmicInspection: OphthalmicInspectionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.ophthalmicInspection.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
