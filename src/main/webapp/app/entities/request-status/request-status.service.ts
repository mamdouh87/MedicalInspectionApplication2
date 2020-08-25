import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRequestStatus } from 'app/shared/model/request-status.model';

type EntityResponseType = HttpResponse<IRequestStatus>;
type EntityArrayResponseType = HttpResponse<IRequestStatus[]>;

@Injectable({ providedIn: 'root' })
export class RequestStatusService {
  public resourceUrl = SERVER_API_URL + 'api/request-statuses';

  constructor(protected http: HttpClient) {}

  create(requestStatus: IRequestStatus): Observable<EntityResponseType> {
    return this.http.post<IRequestStatus>(this.resourceUrl, requestStatus, { observe: 'response' });
  }

  update(requestStatus: IRequestStatus): Observable<EntityResponseType> {
    return this.http.put<IRequestStatus>(this.resourceUrl, requestStatus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRequestStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRequestStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
