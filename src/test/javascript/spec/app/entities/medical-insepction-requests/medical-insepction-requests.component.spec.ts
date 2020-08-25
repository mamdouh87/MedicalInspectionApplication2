import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { MedicalInsepctionRequestsComponent } from 'app/entities/medical-insepction-requests/medical-insepction-requests.component';
import { MedicalInsepctionRequestsService } from 'app/entities/medical-insepction-requests/medical-insepction-requests.service';
import { MedicalInsepctionRequests } from 'app/shared/model/medical-insepction-requests.model';

describe('Component Tests', () => {
  describe('MedicalInsepctionRequests Management Component', () => {
    let comp: MedicalInsepctionRequestsComponent;
    let fixture: ComponentFixture<MedicalInsepctionRequestsComponent>;
    let service: MedicalInsepctionRequestsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [MedicalInsepctionRequestsComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(MedicalInsepctionRequestsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedicalInsepctionRequestsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalInsepctionRequestsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MedicalInsepctionRequests(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.medicalInsepctionRequests && comp.medicalInsepctionRequests[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MedicalInsepctionRequests(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.medicalInsepctionRequests && comp.medicalInsepctionRequests[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
