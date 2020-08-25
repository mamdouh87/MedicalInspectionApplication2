import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRequestBiometricData } from 'app/shared/model/request-biometric-data.model';

type EntityResponseType = HttpResponse<IRequestBiometricData>;
type EntityArrayResponseType = HttpResponse<IRequestBiometricData[]>;

@Injectable({ providedIn: 'root' })
export class RequestBiometricDataService {
  public resourceUrl = SERVER_API_URL + 'api/request-biometric-data';

  constructor(protected http: HttpClient) {}

  create(requestBiometricData: IRequestBiometricData): Observable<EntityResponseType> {
    return this.http.post<IRequestBiometricData>(this.resourceUrl, requestBiometricData, { observe: 'response' });
  }

  update(requestBiometricData: IRequestBiometricData): Observable<EntityResponseType> {
    return this.http.put<IRequestBiometricData>(this.resourceUrl, requestBiometricData, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRequestBiometricData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRequestBiometricData[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
