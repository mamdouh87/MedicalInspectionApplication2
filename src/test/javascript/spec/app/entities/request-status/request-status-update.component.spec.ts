import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { RequestStatusUpdateComponent } from 'app/entities/request-status/request-status-update.component';
import { RequestStatusService } from 'app/entities/request-status/request-status.service';
import { RequestStatus } from 'app/shared/model/request-status.model';

describe('Component Tests', () => {
  describe('RequestStatus Management Update Component', () => {
    let comp: RequestStatusUpdateComponent;
    let fixture: ComponentFixture<RequestStatusUpdateComponent>;
    let service: RequestStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [RequestStatusUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RequestStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RequestStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RequestStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RequestStatus(123);
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
        const entity = new RequestStatus();
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
