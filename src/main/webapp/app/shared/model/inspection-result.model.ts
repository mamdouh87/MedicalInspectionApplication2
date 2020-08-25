export interface IInspectionResult {
  id?: number;
  nameEn?: string;
  nameAr?: string;
  code?: string;
}

export class InspectionResult implements IInspectionResult {
  constructor(public id?: number, public nameEn?: string, public nameAr?: string, public code?: string) {}
}
