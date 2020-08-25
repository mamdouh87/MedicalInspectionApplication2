export interface IRequestBiometricData {
  id?: number;
  imageContentType?: string;
  image?: any;
}

export class RequestBiometricData implements IRequestBiometricData {
  constructor(public id?: number, public imageContentType?: string, public image?: any) {}
}
