import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { AbdominalInseptionUpdateComponent } from 'app/entities/abdominal-inseption/abdominal-inseption-update.component';
import { AbdominalInseptionService } from 'app/entities/abdominal-inseption/abdominal-inseption.service';
import { AbdominalInseption } from 'app/shared/model/abdominal-inseption.model';

describe('Component Tests', () => {
  describe('AbdominalInseption Management Update Component', () => {
    let comp: AbdominalInseptionUpdateComponent;
    let fixture: ComponentFixture<AbdominalInseptionUpdateComponent>;
    let service: AbdominalInseptionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [AbdominalInseptionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AbdominalInseptionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AbdominalInseptionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AbdominalInseptionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AbdominalInseption(123);
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
        const entity = new AbdominalInseption();
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
