import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInspectionType, InspectionType } from 'app/shared/model/inspection-type.model';
import { InspectionTypeService } from './inspection-type.service';
import { InspectionTypeComponent } from './inspection-type.component';
import { InspectionTypeDetailComponent } from './inspection-type-detail.component';
import { InspectionTypeUpdateComponent } from './inspection-type-update.component';

@Injectable({ providedIn: 'root' })
export class InspectionTypeResolve implements Resolve<IInspectionType> {
  constructor(private service: InspectionTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInspectionType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((inspectionType: HttpResponse<InspectionType>) => {
          if (inspectionType.body) {
            return of(inspectionType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InspectionType());
  }
}

export const inspectionTypeRoute: Routes = [
  {
    path: '',
    component: InspectionTypeComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalInspectionApplicationApp.inspectionType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InspectionTypeDetailComponent,
    resolve: {
      inspectionType: InspectionTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.inspectionType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InspectionTypeUpdateComponent,
    resolve: {
      inspectionType: InspectionTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.inspectionType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InspectionTypeUpdateComponent,
    resolve: {
      inspectionType: InspectionTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.inspectionType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
