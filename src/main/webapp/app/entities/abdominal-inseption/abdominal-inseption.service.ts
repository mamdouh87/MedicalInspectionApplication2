import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAbdominalInseption } from 'app/shared/model/abdominal-inseption.model';

type EntityResponseType = HttpResponse<IAbdominalInseption>;
type EntityArrayResponseType = HttpResponse<IAbdominalInseption[]>;

@Injectable({ providedIn: 'root' })
export class AbdominalInseptionService {
  public resourceUrl = SERVER_API_URL + 'api/abdominal-inseptions';

  constructor(protected http: HttpClient) {}

  create(abdominalInseption: IAbdominalInseption): Observable<EntityResponseType> {
    return this.http.post<IAbdominalInseption>(this.resourceUrl, abdominalInseption, { observe: 'response' });
  }

  update(abdominalInseption: IAbdominalInseption): Observable<EntityResponseType> {
    return this.http.put<IAbdominalInseption>(this.resourceUrl, abdominalInseption, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAbdominalInseption>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAbdominalInseption[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
