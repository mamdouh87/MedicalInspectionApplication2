import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRequestBiometricData, RequestBiometricData } from 'app/shared/model/request-biometric-data.model';
import { RequestBiometricDataService } from './request-biometric-data.service';
import { RequestBiometricDataComponent } from './request-biometric-data.component';
import { RequestBiometricDataDetailComponent } from './request-biometric-data-detail.component';
import { RequestBiometricDataUpdateComponent } from './request-biometric-data-update.component';

@Injectable({ providedIn: 'root' })
export class RequestBiometricDataResolve implements Resolve<IRequestBiometricData> {
  constructor(private service: RequestBiometricDataService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRequestBiometricData> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((requestBiometricData: HttpResponse<RequestBiometricData>) => {
          if (requestBiometricData.body) {
            return of(requestBiometricData.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RequestBiometricData());
  }
}

export const requestBiometricDataRoute: Routes = [
  {
    path: '',
    component: RequestBiometricDataComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalInspectionApplicationApp.requestBiometricData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RequestBiometricDataDetailComponent,
    resolve: {
      requestBiometricData: RequestBiometricDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.requestBiometricData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RequestBiometricDataUpdateComponent,
    resolve: {
      requestBiometricData: RequestBiometricDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.requestBiometricData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RequestBiometricDataUpdateComponent,
    resolve: {
      requestBiometricData: RequestBiometricDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.requestBiometricData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
