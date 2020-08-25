import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { InspectionTypeDetailComponent } from 'app/entities/inspection-type/inspection-type-detail.component';
import { InspectionType } from 'app/shared/model/inspection-type.model';

describe('Component Tests', () => {
  describe('InspectionType Management Detail Component', () => {
    let comp: InspectionTypeDetailComponent;
    let fixture: ComponentFixture<InspectionTypeDetailComponent>;
    const route = ({ data: of({ inspectionType: new InspectionType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [InspectionTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InspectionTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InspectionTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load inspectionType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.inspectionType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
