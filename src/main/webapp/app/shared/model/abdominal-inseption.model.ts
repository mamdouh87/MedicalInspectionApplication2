export interface IAbdominalInseption {
  id?: number;
  bloodGroup?: string;
  doctorComments?: string;
  medicalConditionId?: number;
  inspectionResultId?: number;
}

export class AbdominalInseption implements IAbdominalInseption {
  constructor(
    public id?: number,
    public bloodGroup?: string,
    public doctorComments?: string,
    public medicalConditionId?: number,
    public inspectionResultId?: number
  ) {}
}
