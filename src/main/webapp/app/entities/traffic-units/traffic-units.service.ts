import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITrafficUnits } from 'app/shared/model/traffic-units.model';

type EntityResponseType = HttpResponse<ITrafficUnits>;
type EntityArrayResponseType = HttpResponse<ITrafficUnits[]>;

@Injectable({ providedIn: 'root' })
export class TrafficUnitsService {
  public resourceUrl = SERVER_API_URL + 'api/traffic-units';

  constructor(protected http: HttpClient) {}

  create(trafficUnits: ITrafficUnits): Observable<EntityResponseType> {
    return this.http.post<ITrafficUnits>(this.resourceUrl, trafficUnits, { observe: 'response' });
  }

  update(trafficUnits: ITrafficUnits): Observable<EntityResponseType> {
    return this.http.put<ITrafficUnits>(this.resourceUrl, trafficUnits, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITrafficUnits>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITrafficUnits[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
