import { Moment } from 'moment';

export interface IPersons {
  id?: number;
  firstName?: string;
  lastName?: string;
  middleName?: string;
  familyName?: string;
  fullName?: string;
  birthDate?: Moment;
  nationalId?: string;
  passportNumber?: string;
  passportIssueCountryId?: number;
}

export class Persons implements IPersons {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public middleName?: string,
    public familyName?: string,
    public fullName?: string,
    public birthDate?: Moment,
    public nationalId?: string,
    public passportNumber?: string,
    public passportIssueCountryId?: number
  ) {}
}
