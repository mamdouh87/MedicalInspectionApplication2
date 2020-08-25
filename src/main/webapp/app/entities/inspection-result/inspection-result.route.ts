import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInspectionResult, InspectionResult } from 'app/shared/model/inspection-result.model';
import { InspectionResultService } from './inspection-result.service';
import { InspectionResultComponent } from './inspection-result.component';
import { InspectionResultDetailComponent } from './inspection-result-detail.component';
import { InspectionResultUpdateComponent } from './inspection-result-update.component';

@Injectable({ providedIn: 'root' })
export class InspectionResultResolve implements Resolve<IInspectionResult> {
  constructor(private service: InspectionResultService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInspectionResult> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((inspectionResult: HttpResponse<InspectionResult>) => {
          if (inspectionResult.body) {
            return of(inspectionResult.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InspectionResult());
  }
}

export const inspectionResultRoute: Routes = [
  {
    path: '',
    component: InspectionResultComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalInspectionApplicationApp.inspectionResult.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InspectionResultDetailComponent,
    resolve: {
      inspectionResult: InspectionResultResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.inspectionResult.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InspectionResultUpdateComponent,
    resolve: {
      inspectionResult: InspectionResultResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.inspectionResult.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InspectionResultUpdateComponent,
    resolve: {
      inspectionResult: InspectionResultResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.inspectionResult.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
