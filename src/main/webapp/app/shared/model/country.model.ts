export interface ICountry {
  id?: number;
  countryNameEn?: string;
  countryNameAr?: string;
  countryCode?: string;
}

export class Country implements ICountry {
  constructor(public id?: number, public countryNameEn?: string, public countryNameAr?: string, public countryCode?: string) {}
}
