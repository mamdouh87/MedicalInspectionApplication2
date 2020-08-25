export interface IMedicalCondition {
  id?: number;
  conditionNameEn?: string;
  conditionNameAr?: string;
  inspectionTypeId?: number;
}

export class MedicalCondition implements IMedicalCondition {
  constructor(public id?: number, public conditionNameEn?: string, public conditionNameAr?: string, public inspectionTypeId?: number) {}
}
