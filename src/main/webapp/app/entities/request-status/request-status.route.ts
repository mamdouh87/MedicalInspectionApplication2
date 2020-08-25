import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRequestStatus, RequestStatus } from 'app/shared/model/request-status.model';
import { RequestStatusService } from './request-status.service';
import { RequestStatusComponent } from './request-status.component';
import { RequestStatusDetailComponent } from './request-status-detail.component';
import { RequestStatusUpdateComponent } from './request-status-update.component';

@Injectable({ providedIn: 'root' })
export class RequestStatusResolve implements Resolve<IRequestStatus> {
  constructor(private service: RequestStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRequestStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((requestStatus: HttpResponse<RequestStatus>) => {
          if (requestStatus.body) {
            return of(requestStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RequestStatus());
  }
}

export const requestStatusRoute: Routes = [
  {
    path: '',
    component: RequestStatusComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalInspectionApplicationApp.requestStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RequestStatusDetailComponent,
    resolve: {
      requestStatus: RequestStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.requestStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RequestStatusUpdateComponent,
    resolve: {
      requestStatus: RequestStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.requestStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RequestStatusUpdateComponent,
    resolve: {
      requestStatus: RequestStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.requestStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
