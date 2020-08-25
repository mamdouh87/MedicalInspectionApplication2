export interface ILicenseCategory {
  id?: number;
  nameEn?: string;
  nameAr?: string;
  code?: string;
}

export class LicenseCategory implements ILicenseCategory {
  constructor(public id?: number, public nameEn?: string, public nameAr?: string, public code?: string) {}
}
