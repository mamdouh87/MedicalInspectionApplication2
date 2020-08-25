import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInspectionType } from 'app/shared/model/inspection-type.model';

type EntityResponseType = HttpResponse<IInspectionType>;
type EntityArrayResponseType = HttpResponse<IInspectionType[]>;

@Injectable({ providedIn: 'root' })
export class InspectionTypeService {
  public resourceUrl = SERVER_API_URL + 'api/inspection-types';

  constructor(protected http: HttpClient) {}

  create(inspectionType: IInspectionType): Observable<EntityResponseType> {
    return this.http.post<IInspectionType>(this.resourceUrl, inspectionType, { observe: 'response' });
  }

  update(inspectionType: IInspectionType): Observable<EntityResponseType> {
    return this.http.put<IInspectionType>(this.resourceUrl, inspectionType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInspectionType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInspectionType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
