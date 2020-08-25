export interface IInspectionType {
  id?: number;
  nameEn?: string;
  nameAr?: string;
  code?: string;
}

export class InspectionType implements IInspectionType {
  constructor(public id?: number, public nameEn?: string, public nameAr?: string, public code?: string) {}
}
