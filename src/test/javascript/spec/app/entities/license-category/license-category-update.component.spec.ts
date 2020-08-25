import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { LicenseCategoryUpdateComponent } from 'app/entities/license-category/license-category-update.component';
import { LicenseCategoryService } from 'app/entities/license-category/license-category.service';
import { LicenseCategory } from 'app/shared/model/license-category.model';

describe('Component Tests', () => {
  describe('LicenseCategory Management Update Component', () => {
    let comp: LicenseCategoryUpdateComponent;
    let fixture: ComponentFixture<LicenseCategoryUpdateComponent>;
    let service: LicenseCategoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [LicenseCategoryUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(LicenseCategoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LicenseCategoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LicenseCategoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LicenseCategory(123);
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
        const entity = new LicenseCategory();
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
