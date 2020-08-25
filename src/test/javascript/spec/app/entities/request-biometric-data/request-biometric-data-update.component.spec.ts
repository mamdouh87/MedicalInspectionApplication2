import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { RequestBiometricDataUpdateComponent } from 'app/entities/request-biometric-data/request-biometric-data-update.component';
import { RequestBiometricDataService } from 'app/entities/request-biometric-data/request-biometric-data.service';
import { RequestBiometricData } from 'app/shared/model/request-biometric-data.model';

describe('Component Tests', () => {
  describe('RequestBiometricData Management Update Component', () => {
    let comp: RequestBiometricDataUpdateComponent;
    let fixture: ComponentFixture<RequestBiometricDataUpdateComponent>;
    let service: RequestBiometricDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [RequestBiometricDataUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RequestBiometricDataUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RequestBiometricDataUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RequestBiometricDataService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RequestBiometricData(123);
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
        const entity = new RequestBiometricData();
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
