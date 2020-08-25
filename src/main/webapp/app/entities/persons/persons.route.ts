import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPersons, Persons } from 'app/shared/model/persons.model';
import { PersonsService } from './persons.service';
import { PersonsComponent } from './persons.component';
import { PersonsDetailComponent } from './persons-detail.component';
import { PersonsUpdateComponent } from './persons-update.component';

@Injectable({ providedIn: 'root' })
export class PersonsResolve implements Resolve<IPersons> {
  constructor(private service: PersonsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPersons> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((persons: HttpResponse<Persons>) => {
          if (persons.body) {
            return of(persons.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Persons());
  }
}

export const personsRoute: Routes = [
  {
    path: '',
    component: PersonsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalInspectionApplicationApp.persons.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PersonsDetailComponent,
    resolve: {
      persons: PersonsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.persons.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PersonsUpdateComponent,
    resolve: {
      persons: PersonsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.persons.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PersonsUpdateComponent,
    resolve: {
      persons: PersonsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.persons.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
