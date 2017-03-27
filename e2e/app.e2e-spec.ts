import { SensduPage } from './app.po';

describe('sensdu App', function() {
  let page: SensduPage;

  beforeEach(() => {
    page = new SensduPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
