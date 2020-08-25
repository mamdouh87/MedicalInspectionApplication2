import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { OphthalmicInspectionDetailComponent } from 'app/entities/ophthalmic-inspection/ophthalmic-inspection-detail.component';
import { OphthalmicInspection } from 'app/shared/model/ophthalmic-inspection.model';

describe('Component Tests', () => {
  describe('OphthalmicInspection Management Detail Component', () => {
    let comp: OphthalmicInspectionDetailComponent;
    let fixture: ComponentFixture<OphthalmicInspectionDetailComponent>;
    const route = ({ data: of({ ophthalmicInspection: new OphthalmicInspection(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [OphthalmicInspectionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OphthalmicInspectionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OphthalmicInspectionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ophthalmicInspection on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ophthalmicInspection).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
