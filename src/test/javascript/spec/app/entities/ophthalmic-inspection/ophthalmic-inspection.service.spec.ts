import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { OphthalmicInspectionService } from 'app/entities/ophthalmic-inspection/ophthalmic-inspection.service';
import { IOphthalmicInspection, OphthalmicInspection } from 'app/shared/model/ophthalmic-inspection.model';

describe('Service Tests', () => {
  describe('OphthalmicInspection Service', () => {
    let injector: TestBed;
    let service: OphthalmicInspectionService;
    let httpMock: HttpTestingController;
    let elemDefault: IOphthalmicInspection;
    let expectedResult: IOphthalmicInspection | IOphthalmicInspection[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OphthalmicInspectionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new OphthalmicInspection(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a OphthalmicInspection', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new OphthalmicInspection()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OphthalmicInspection', () => {
        const returnedFromService = Object.assign(
          {
            rigthEye: 'BBBBBB',
            leftEye: 'BBBBBB',
            doctorComments: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of OphthalmicInspection', () => {
        const returnedFromService = Object.assign(
          {
            rigthEye: 'BBBBBB',
            leftEye: 'BBBBBB',
            doctorComments: 'BBBBBB',
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

      it('should delete a OphthalmicInspection', () => {
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
