import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInspectionResult } from 'app/shared/model/inspection-result.model';

type EntityResponseType = HttpResponse<IInspectionResult>;
type EntityArrayResponseType = HttpResponse<IInspectionResult[]>;

@Injectable({ providedIn: 'root' })
export class InspectionResultService {
  public resourceUrl = SERVER_API_URL + 'api/inspection-results';

  constructor(protected http: HttpClient) {}

  create(inspectionResult: IInspectionResult): Observable<EntityResponseType> {
    return this.http.post<IInspectionResult>(this.resourceUrl, inspectionResult, { observe: 'response' });
  }

  update(inspectionResult: IInspectionResult): Observable<EntityResponseType> {
    return this.http.put<IInspectionResult>(this.resourceUrl, inspectionResult, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInspectionResult>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInspectionResult[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
