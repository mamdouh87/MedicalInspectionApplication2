import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { MedicalInsepctionRequestsUpdateComponent } from 'app/entities/medical-insepction-requests/medical-insepction-requests-update.component';
import { MedicalInsepctionRequestsService } from 'app/entities/medical-insepction-requests/medical-insepction-requests.service';
import { MedicalInsepctionRequests } from 'app/shared/model/medical-insepction-requests.model';

describe('Component Tests', () => {
  describe('MedicalInsepctionRequests Management Update Component', () => {
    let comp: MedicalInsepctionRequestsUpdateComponent;
    let fixture: ComponentFixture<MedicalInsepctionRequestsUpdateComponent>;
    let service: MedicalInsepctionRequestsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [MedicalInsepctionRequestsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MedicalInsepctionRequestsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedicalInsepctionRequestsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalInsepctionRequestsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedicalInsepctionRequests(123);
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
        const entity = new MedicalInsepctionRequests();
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
