import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { InspectionRequirementUpdateComponent } from 'app/entities/inspection-requirement/inspection-requirement-update.component';
import { InspectionRequirementService } from 'app/entities/inspection-requirement/inspection-requirement.service';
import { InspectionRequirement } from 'app/shared/model/inspection-requirement.model';

describe('Component Tests', () => {
  describe('InspectionRequirement Management Update Component', () => {
    let comp: InspectionRequirementUpdateComponent;
    let fixture: ComponentFixture<InspectionRequirementUpdateComponent>;
    let service: InspectionRequirementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [InspectionRequirementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InspectionRequirementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InspectionRequirementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InspectionRequirementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InspectionRequirement(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new InspectionRequirement();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
