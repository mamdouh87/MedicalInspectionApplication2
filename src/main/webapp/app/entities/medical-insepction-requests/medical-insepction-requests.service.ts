import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMedicalInsepctionRequests } from 'app/shared/model/medical-insepction-requests.model';

type EntityResponseType = HttpResponse<IMedicalInsepctionRequests>;
type EntityArrayResponseType = HttpResponse<IMedicalInsepctionRequests[]>;

@Injectable({ providedIn: 'root' })
export class MedicalInsepctionRequestsService {
  public resourceUrl = SERVER_API_URL + 'api/medical-insepction-requests';

  constructor(protected http: HttpClient) {}

  create(medicalInsepctionRequests: IMedicalInsepctionRequests): Observable<EntityResponseType> {
    return this.http.post<IMedicalInsepctionRequests>(this.resourceUrl, medicalInsepctionRequests, { observe: 'response' });
  }

  update(medicalInsepctionRequests: IMedicalInsepctionRequests): Observable<EntityResponseType> {
    return this.http.put<IMedicalInsepctionRequests>(this.resourceUrl, medicalInsepctionRequests, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMedicalInsepctionRequests>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMedicalInsepctionRequests[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
