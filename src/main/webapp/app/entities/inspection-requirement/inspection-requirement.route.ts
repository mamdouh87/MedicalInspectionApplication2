import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInspectionRequirement, InspectionRequirement } from 'app/shared/model/inspection-requirement.model';
import { InspectionRequirementService } from './inspection-requirement.service';
import { InspectionRequirementComponent } from './inspection-requirement.component';
import { InspectionRequirementDetailComponent } from './inspection-requirement-detail.component';
import { InspectionRequirementUpdateComponent } from './inspection-requirement-update.component';

@Injectable({ providedIn: 'root' })
export class InspectionRequirementResolve implements Resolve<IInspectionRequirement> {
  constructor(private service: InspectionRequirementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInspectionRequirement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((inspectionRequirement: HttpResponse<InspectionRequirement>) => {
          if (inspectionRequirement.body) {
            return of(inspectionRequirement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InspectionRequirement());
  }
}

export const inspectionRequirementRoute: Routes = [
  {
    path: '',
    component: InspectionRequirementComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalInspectionApplicationApp.inspectionRequirement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InspectionRequirementDetailComponent,
    resolve: {
      inspectionRequirement: InspectionRequirementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.inspectionRequirement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InspectionRequirementUpdateComponent,
    resolve: {
      inspectionRequirement: InspectionRequirementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.inspectionRequirement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InspectionRequirementUpdateComponent,
    resolve: {
      inspectionRequirement: InspectionRequirementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.inspectionRequirement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
