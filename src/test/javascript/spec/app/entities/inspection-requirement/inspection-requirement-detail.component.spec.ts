import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { InspectionRequirementDetailComponent } from 'app/entities/inspection-requirement/inspection-requirement-detail.component';
import { InspectionRequirement } from 'app/shared/model/inspection-requirement.model';

describe('Component Tests', () => {
  describe('InspectionRequirement Management Detail Component', () => {
    let comp: InspectionRequirementDetailComponent;
    let fixture: ComponentFixture<InspectionRequirementDetailComponent>;
    const route = ({ data: of({ inspectionRequirement: new InspectionRequirement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [InspectionRequirementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InspectionRequirementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InspectionRequirementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load inspectionRequirement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.inspectionRequirement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
