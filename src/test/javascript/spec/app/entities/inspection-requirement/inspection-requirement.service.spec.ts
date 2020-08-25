import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { InspectionRequirementService } from 'app/entities/inspection-requirement/inspection-requirement.service';
import { IInspectionRequirement, InspectionRequirement } from 'app/shared/model/inspection-requirement.model';

describe('Service Tests', () => {
  describe('InspectionRequirement Service', () => {
    let injector: TestBed;
    let service: InspectionRequirementService;
    let httpMock: HttpTestingController;
    let elemDefault: IInspectionRequirement;
    let expectedResult: IInspectionRequirement | IInspectionRequirement[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InspectionRequirementService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new InspectionRequirement(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a InspectionRequirement', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new InspectionRequirement()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a InspectionRequirement', () => {
        const returnedFromService = Object.assign(
          {
            nameEn: 'BBBBBB',
            nameAr: 'BBBBBB',
            code: 'BBBBBB',
            order: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of InspectionRequirement', () => {
        const returnedFromService = Object.assign(
          {
            nameEn: 'BBBBBB',
            nameAr: 'BBBBBB',
            code: 'BBBBBB',
            order: 1,
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

      it('should delete a InspectionRequirement', () => {
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
