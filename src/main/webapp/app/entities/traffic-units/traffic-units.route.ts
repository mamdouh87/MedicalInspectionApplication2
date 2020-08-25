import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITrafficUnits, TrafficUnits } from 'app/shared/model/traffic-units.model';
import { TrafficUnitsService } from './traffic-units.service';
import { TrafficUnitsComponent } from './traffic-units.component';
import { TrafficUnitsDetailComponent } from './traffic-units-detail.component';
import { TrafficUnitsUpdateComponent } from './traffic-units-update.component';

@Injectable({ providedIn: 'root' })
export class TrafficUnitsResolve implements Resolve<ITrafficUnits> {
  constructor(private service: TrafficUnitsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITrafficUnits> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((trafficUnits: HttpResponse<TrafficUnits>) => {
          if (trafficUnits.body) {
            return of(trafficUnits.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TrafficUnits());
  }
}

export const trafficUnitsRoute: Routes = [
  {
    path: '',
    component: TrafficUnitsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalInspectionApplicationApp.trafficUnits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TrafficUnitsDetailComponent,
    resolve: {
      trafficUnits: TrafficUnitsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.trafficUnits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TrafficUnitsUpdateComponent,
    resolve: {
      trafficUnits: TrafficUnitsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.trafficUnits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TrafficUnitsUpdateComponent,
    resolve: {
      trafficUnits: TrafficUnitsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.trafficUnits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
