import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { InspectionTypeUpdateComponent } from 'app/entities/inspection-type/inspection-type-update.component';
import { InspectionTypeService } from 'app/entities/inspection-type/inspection-type.service';
import { InspectionType } from 'app/shared/model/inspection-type.model';

describe('Component Tests', () => {
  describe('InspectionType Management Update Component', () => {
    let comp: InspectionTypeUpdateComponent;
    let fixture: ComponentFixture<InspectionTypeUpdateComponent>;
    let service: InspectionTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [InspectionTypeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InspectionTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InspectionTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InspectionTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InspectionType(123);
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
        const entity = new InspectionType();
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
