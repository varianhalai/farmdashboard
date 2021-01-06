import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AllStatsDialogComponent} from './all-stats-dialog.component';

describe('AllStatsDialogComponent', () => {
  let component: AllStatsDialogComponent;
  let fixture: ComponentFixture<AllStatsDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AllStatsDialogComponent]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllStatsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
