import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAbdominalInseption, AbdominalInseption } from 'app/shared/model/abdominal-inseption.model';
import { AbdominalInseptionService } from './abdominal-inseption.service';
import { AbdominalInseptionComponent } from './abdominal-inseption.component';
import { AbdominalInseptionDetailComponent } from './abdominal-inseption-detail.component';
import { AbdominalInseptionUpdateComponent } from './abdominal-inseption-update.component';

@Injectable({ providedIn: 'root' })
export class AbdominalInseptionResolve implements Resolve<IAbdominalInseption> {
  constructor(private service: AbdominalInseptionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAbdominalInseption> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((abdominalInseption: HttpResponse<AbdominalInseption>) => {
          if (abdominalInseption.body) {
            return of(abdominalInseption.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AbdominalInseption());
  }
}

export const abdominalInseptionRoute: Routes = [
  {
    path: '',
    component: AbdominalInseptionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalInspectionApplicationApp.abdominalInseption.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AbdominalInseptionDetailComponent,
    resolve: {
      abdominalInseption: AbdominalInseptionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.abdominalInseption.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AbdominalInseptionUpdateComponent,
    resolve: {
      abdominalInseption: AbdominalInseptionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.abdominalInseption.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AbdominalInseptionUpdateComponent,
    resolve: {
      abdominalInseption: AbdominalInseptionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.abdominalInseption.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
