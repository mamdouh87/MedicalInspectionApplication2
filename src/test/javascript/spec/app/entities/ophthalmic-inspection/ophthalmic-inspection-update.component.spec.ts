import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { OphthalmicInspectionUpdateComponent } from 'app/entities/ophthalmic-inspection/ophthalmic-inspection-update.component';
import { OphthalmicInspectionService } from 'app/entities/ophthalmic-inspection/ophthalmic-inspection.service';
import { OphthalmicInspection } from 'app/shared/model/ophthalmic-inspection.model';

describe('Component Tests', () => {
  describe('OphthalmicInspection Management Update Component', () => {
    let comp: OphthalmicInspectionUpdateComponent;
    let fixture: ComponentFixture<OphthalmicInspectionUpdateComponent>;
    let service: OphthalmicInspectionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [OphthalmicInspectionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OphthalmicInspectionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OphthalmicInspectionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OphthalmicInspectionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OphthalmicInspection(123);
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
        const entity = new OphthalmicInspection();
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
