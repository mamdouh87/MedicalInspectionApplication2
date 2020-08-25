import { IMedicalInsepctionRequests } from 'app/shared/model/medical-insepction-requests.model';

export interface IInspectionRequirement {
  id?: number;
  nameEn?: string;
  nameAr?: string;
  code?: string;
  order?: number;
  requests?: IMedicalInsepctionRequests[];
}

export class InspectionRequirement implements IInspectionRequirement {
  constructor(
    public id?: number,
    public nameEn?: string,
    public nameAr?: string,
    public code?: string,
    public order?: number,
    public requests?: IMedicalInsepctionRequests[]
  ) {}
}
