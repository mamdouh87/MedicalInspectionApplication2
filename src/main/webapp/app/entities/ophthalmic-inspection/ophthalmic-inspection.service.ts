import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOphthalmicInspection } from 'app/shared/model/ophthalmic-inspection.model';

type EntityResponseType = HttpResponse<IOphthalmicInspection>;
type EntityArrayResponseType = HttpResponse<IOphthalmicInspection[]>;

@Injectable({ providedIn: 'root' })
export class OphthalmicInspectionService {
  public resourceUrl = SERVER_API_URL + 'api/ophthalmic-inspections';

  constructor(protected http: HttpClient) {}

  create(ophthalmicInspection: IOphthalmicInspection): Observable<EntityResponseType> {
    return this.http.post<IOphthalmicInspection>(this.resourceUrl, ophthalmicInspection, { observe: 'response' });
  }

  update(ophthalmicInspection: IOphthalmicInspection): Observable<EntityResponseType> {
    return this.http.put<IOphthalmicInspection>(this.resourceUrl, ophthalmicInspection, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOphthalmicInspection>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOphthalmicInspection[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
