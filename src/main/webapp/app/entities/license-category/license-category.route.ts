import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILicenseCategory, LicenseCategory } from 'app/shared/model/license-category.model';
import { LicenseCategoryService } from './license-category.service';
import { LicenseCategoryComponent } from './license-category.component';
import { LicenseCategoryDetailComponent } from './license-category-detail.component';
import { LicenseCategoryUpdateComponent } from './license-category-update.component';

@Injectable({ providedIn: 'root' })
export class LicenseCategoryResolve implements Resolve<ILicenseCategory> {
  constructor(private service: LicenseCategoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILicenseCategory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((licenseCategory: HttpResponse<LicenseCategory>) => {
          if (licenseCategory.body) {
            return of(licenseCategory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LicenseCategory());
  }
}

export const licenseCategoryRoute: Routes = [
  {
    path: '',
    component: LicenseCategoryComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalInspectionApplicationApp.licenseCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LicenseCategoryDetailComponent,
    resolve: {
      licenseCategory: LicenseCategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.licenseCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LicenseCategoryUpdateComponent,
    resolve: {
      licenseCategory: LicenseCategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.licenseCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LicenseCategoryUpdateComponent,
    resolve: {
      licenseCategory: LicenseCategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.licenseCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
