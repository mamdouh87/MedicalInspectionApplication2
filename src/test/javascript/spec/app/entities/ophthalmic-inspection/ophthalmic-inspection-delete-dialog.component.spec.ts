import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { OphthalmicInspectionDeleteDialogComponent } from 'app/entities/ophthalmic-inspection/ophthalmic-inspection-delete-dialog.component';
import { OphthalmicInspectionService } from 'app/entities/ophthalmic-inspection/ophthalmic-inspection.service';

describe('Component Tests', () => {
  describe('OphthalmicInspection Management Delete Component', () => {
    let comp: OphthalmicInspectionDeleteDialogComponent;
    let fixture: ComponentFixture<OphthalmicInspectionDeleteDialogComponent>;
    let service: OphthalmicInspectionService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [OphthalmicInspectionDeleteDialogComponent],
      })
        .overrideTemplate(OphthalmicInspectionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OphthalmicInspectionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OphthalmicInspectionService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
