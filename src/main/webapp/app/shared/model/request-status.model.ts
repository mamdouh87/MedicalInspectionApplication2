export interface IRequestStatus {
  id?: number;
  nameEn?: string;
  nameAr?: string;
  code?: string;
}

export class RequestStatus implements IRequestStatus {
  constructor(public id?: number, public nameEn?: string, public nameAr?: string, public code?: string) {}
}
