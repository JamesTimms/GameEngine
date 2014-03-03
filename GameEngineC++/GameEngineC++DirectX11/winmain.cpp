#include "DXApp.h"
#include <tchar.h>

class TestApp : public DXApp {
public:
	TestApp( HINSTANCE hInstance );
	~TestApp( );

	bool Init( ) override;
	void Update( float dt ) override;
	void Render( float dt ) override;
};

TestApp::TestApp(HINSTANCE hInstance) : DXApp( hInstance ) {

}

TestApp::~TestApp( ) {

}

bool TestApp::Init()
{
	return DXApp::Init( );
}

void TestApp::Update(float dt)
{

}

void TestApp::Render(float dt)
{
	m_pImmediateContext->ClearRenderTargetView( m_pRenderTargetView, DirectX::Colors::MediumPurple );

	HR( m_pSwapChain->Present( 0, 0 ) );
}

int WINAPI WinMain( _In_ HINSTANCE hInstance, _In_opt_ HINSTANCE hPrevInstance, _In_ LPSTR lpCmdLine, _In_ int nShowCmd ) {
	TestApp tApp( hInstance );
	if( !tApp.Init( ) ) return 1;
	return tApp.Run( );
	//LPCWSTR title = _T( "Test" );
	//LPCWSTR content = _T( "Hello, World" );
	//MessageBox( NULL, content, title, NULL );
	return 0;
}