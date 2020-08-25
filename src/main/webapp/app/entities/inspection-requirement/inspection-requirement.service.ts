import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInspectionRequirement } from 'app/shared/model/inspection-requirement.model';

type EntityResponseType = HttpResponse<IInspectionRequirement>;
type EntityArrayResponseType = HttpResponse<IInspectionRequirement[]>;

@Injectable({ providedIn: 'root' })
export class InspectionRequirementService {
  public resourceUrl = SERVER_API_URL + 'api/inspection-requirements';

  constructor(protected http: HttpClient) {}

  create(inspectionRequirement: IInspectionRequirement): Observable<EntityResponseType> {
    return this.http.post<IInspectionRequirement>(this.resourceUrl, inspectionRequirement, { observe: 'response' });
  }

  update(inspectionRequirement: IInspectionRequirement): Observable<EntityResponseType> {
    return this.http.put<IInspectionRequirement>(this.resourceUrl, inspectionRequirement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInspectionRequirement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInspectionRequirement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
