import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPersons } from 'app/shared/model/persons.model';

type EntityResponseType = HttpResponse<IPersons>;
type EntityArrayResponseType = HttpResponse<IPersons[]>;

@Injectable({ providedIn: 'root' })
export class PersonsService {
  public resourceUrl = SERVER_API_URL + 'api/persons';

  constructor(protected http: HttpClient) {}

  create(persons: IPersons): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(persons);
    return this.http
      .post<IPersons>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(persons: IPersons): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(persons);
    return this.http
      .put<IPersons>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPersons>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPersons[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(persons: IPersons): IPersons {
    const copy: IPersons = Object.assign({}, persons, {
      birthDate: persons.birthDate && persons.birthDate.isValid() ? persons.birthDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.birthDate = res.body.birthDate ? moment(res.body.birthDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((persons: IPersons) => {
        persons.birthDate = persons.birthDate ? moment(persons.birthDate) : undefined;
      });
    }
    return res;
  }
}
