import { IInspectionRequirement } from 'app/shared/model/inspection-requirement.model';

export interface IMedicalInsepctionRequests {
  id?: number;
  requestNumber?: string;
  exported?: boolean;
  abdominalInseptionId?: number;
  ophthalmicInspectionId?: number;
  personId?: number;
  biometricdataId?: number;
  licenseCategoryId?: number;
  transacionTypeId?: number;
  trafficUnitId?: number;
  statusId?: number;
  requirements?: IInspectionRequirement[];
}

export class MedicalInsepctionRequests implements IMedicalInsepctionRequests {
  constructor(
    public id?: number,
    public requestNumber?: string,
    public exported?: boolean,
    public abdominalInseptionId?: number,
    public ophthalmicInspectionId?: number,
    public personId?: number,
    public biometricdataId?: number,
    public licenseCategoryId?: number,
    public transacionTypeId?: number,
    public trafficUnitId?: number,
    public statusId?: number,
    public requirements?: IInspectionRequirement[]
  ) {
    this.exported = this.exported || false;
  }
}
