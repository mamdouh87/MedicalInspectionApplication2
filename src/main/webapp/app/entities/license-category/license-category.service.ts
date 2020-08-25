import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILicenseCategory } from 'app/shared/model/license-category.model';

type EntityResponseType = HttpResponse<ILicenseCategory>;
type EntityArrayResponseType = HttpResponse<ILicenseCategory[]>;

@Injectable({ providedIn: 'root' })
export class LicenseCategoryService {
  public resourceUrl = SERVER_API_URL + 'api/license-categories';

  constructor(protected http: HttpClient) {}

  create(licenseCategory: ILicenseCategory): Observable<EntityResponseType> {
    return this.http.post<ILicenseCategory>(this.resourceUrl, licenseCategory, { observe: 'response' });
  }

  update(licenseCategory: ILicenseCategory): Observable<EntityResponseType> {
    return this.http.put<ILicenseCategory>(this.resourceUrl, licenseCategory, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILicenseCategory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILicenseCategory[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
