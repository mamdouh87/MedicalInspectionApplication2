import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { MedicalInsepctionRequestsDeleteDialogComponent } from 'app/entities/medical-insepction-requests/medical-insepction-requests-delete-dialog.component';
import { MedicalInsepctionRequestsService } from 'app/entities/medical-insepction-requests/medical-insepction-requests.service';

describe('Component Tests', () => {
  describe('MedicalInsepctionRequests Management Delete Component', () => {
    let comp: MedicalInsepctionRequestsDeleteDialogComponent;
    let fixture: ComponentFixture<MedicalInsepctionRequestsDeleteDialogComponent>;
    let service: MedicalInsepctionRequestsService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [MedicalInsepctionRequestsDeleteDialogComponent],
      })
        .overrideTemplate(MedicalInsepctionRequestsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedicalInsepctionRequestsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalInsepctionRequestsService);
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
