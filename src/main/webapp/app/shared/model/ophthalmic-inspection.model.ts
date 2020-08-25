export interface IOphthalmicInspection {
  id?: number;
  rigthEye?: string;
  leftEye?: string;
  doctorComments?: string;
  medicalConditionId?: number;
  inspectionResultId?: number;
}

export class OphthalmicInspection implements IOphthalmicInspection {
  constructor(
    public id?: number,
    public rigthEye?: string,
    public leftEye?: string,
    public doctorComments?: string,
    public medicalConditionId?: number,
    public inspectionResultId?: number
  ) {}
}
