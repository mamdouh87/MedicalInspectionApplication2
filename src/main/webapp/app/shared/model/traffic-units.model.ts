export interface ITrafficUnits {
  id?: number;
  trafficUnitNameEn?: string;
  trafficUnitNameAr?: string;
  trafficUnitCode?: string;
}

export class TrafficUnits implements ITrafficUnits {
  constructor(public id?: number, public trafficUnitNameEn?: string, public trafficUnitNameAr?: string, public trafficUnitCode?: string) {}
}
