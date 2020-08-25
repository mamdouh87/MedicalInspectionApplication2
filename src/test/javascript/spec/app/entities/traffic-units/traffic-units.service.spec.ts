import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TrafficUnitsService } from 'app/entities/traffic-units/traffic-units.service';
import { ITrafficUnits, TrafficUnits } from 'app/shared/model/traffic-units.model';

describe('Service Tests', () => {
  describe('TrafficUnits Service', () => {
    let injector: TestBed;
    let service: TrafficUnitsService;
    let httpMock: HttpTestingController;
    let elemDefault: ITrafficUnits;
    let expectedResult: ITrafficUnits | ITrafficUnits[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TrafficUnitsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TrafficUnits(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TrafficUnits', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TrafficUnits()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TrafficUnits', () => {
        const returnedFromService = Object.assign(
          {
            trafficUnitNameEn: 'BBBBBB',
            trafficUnitNameAr: 'BBBBBB',
            trafficUnitCode: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TrafficUnits', () => {
        const returnedFromService = Object.assign(
          {
            trafficUnitNameEn: 'BBBBBB',
            trafficUnitNameAr: 'BBBBBB',
            trafficUnitCode: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TrafficUnits', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
