import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { InspectionResultDetailComponent } from 'app/entities/inspection-result/inspection-result-detail.component';
import { InspectionResult } from 'app/shared/model/inspection-result.model';

describe('Component Tests', () => {
  describe('InspectionResult Management Detail Component', () => {
    let comp: InspectionResultDetailComponent;
    let fixture: ComponentFixture<InspectionResultDetailComponent>;
    const route = ({ data: of({ inspectionResult: new InspectionResult(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [InspectionResultDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InspectionResultDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InspectionResultDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load inspectionResult on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.inspectionResult).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
