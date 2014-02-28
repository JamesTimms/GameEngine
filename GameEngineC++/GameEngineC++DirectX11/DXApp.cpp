#include "DXApp.h"
#include <stdlib.h>
#include <string.h>
#include <tchar.h>

namespace {
	//USED TO FORWARD MSGS TO USER DEFINED PROC FUNCTION
	DXApp* g_pApp = nullptr;
}

LRESULT CALLBACK MainWndProc( HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam ) {
	if( g_pApp ) return g_pApp->MsgProc( hwnd, msg, wParam, lParam );
	else return DefWindowProc( hwnd, msg, wParam, lParam );
}

DXApp::DXApp( HINSTANCE hInstance ) {
	m_hAppInstance = hInstance;
	m_hAppWnd = NULL;
	m_ClientWidth = 800;
	m_ClientHieght = 600;
	m_AppTitle = "DIRECTX 11 WINDOW TITLE";
	m_WndStyle = WS_OVERLAPPEDWINDOW;
	g_pApp = this;

	m_pDevice = nullptr;
	m_pImmediateContext = nullptr;
	m_pRenderTargetView =nullptr;
	m_pSwapChain =nullptr;
}


DXApp::~DXApp(void) {
	//CLEANUP DIRECT3D
	if( m_pImmediateContext ) m_pImmediateContext->ClearState( );
	Memory::SafeRelease( m_pRenderTargetView );
	Memory::SafeRelease( m_pSwapChain );
	Memory::SafeRelease( m_pImmediateContext );
	Memory::SafeRelease( m_pDevice );
}

int DXApp::Run( ) {
	MSG msg = { 0 };
	while( WM_QUIT != msg.message ) {
		if( PeekMessage( &msg, NULL, NULL, NULL, PM_REMOVE ) ) {
			TranslateMessage( &msg );
			DispatchMessage( &msg );
		} else {
			//Update
			Update( 0.0f );
			//Render
			Render( 0.0f );
		}
	}

	return static_cast<int>( msg.wParam );
}

bool DXApp::Init( ) {
	if( !InitWindow( ) ) {
		return false;
	}

	if( !InitDirect3D( ) ) {
		return false;
	}

	return true;
}

bool DXApp::InitWindow()
{
	//WNDCLASSEX
	WNDCLASSEX wcex;
	ZeroMemory( &wcex, sizeof( WNDCLASSEX ) );
	wcex.cbClsExtra = 0;
	wcex.cbWndExtra = 0;
	wcex.cbSize = sizeof( WNDCLASSEX );
	wcex.style = CS_HREDRAW | CS_VREDRAW;
	wcex.hInstance = m_hAppInstance;
	wcex.lpfnWndProc = MainWndProc;
	wcex.hIcon = LoadIcon( NULL, IDI_APPLICATION );
	wcex.hCursor = LoadIcon( NULL, IDC_ARROW );
	wcex.hbrBackground = ( HBRUSH )GetStockObject( NULL_BRUSH );
	wcex.lpszClassName = _T( "DXAPPWNDCLASS" );
	wcex.hIconSm = LoadIcon( NULL, IDC_ARROW );

	if( !RegisterClassEx( &wcex ) ) {
		OutputDebugString( _T( "\nFAILED TO CREATE WINDOW CLASS \n" ) );
		return false;
	}

	RECT r = { 0, 0, m_ClientWidth, m_ClientHieght };
	AdjustWindowRect( &r, m_WndStyle, FALSE );
	UINT width = r.right - r.left;
	UINT height = r.bottom - r.top;

	UINT x = GetSystemMetrics( SM_CXSCREEN ) / 2 - width / 2;
	UINT y = GetSystemMetrics( SM_CXSCREEN ) / 2 - height / 2;
	static TCHAR szWindowClass[] = _T("DXAPPWNDCLASS");
	
	m_hAppWnd = CreateWindow( szWindowClass,
		szWindowClass,
		m_WndStyle, 
		CW_USEDEFAULT,
		CW_USEDEFAULT,
		width,
		height,
		NULL,
		NULL,
		m_hAppInstance,
		NULL );
	if( !m_hAppWnd ) {
		OutputDebugString( L"\nFAILED TO CREATE WINDOW!" );
		return false;
	}
	ShowWindow( m_hAppWnd, SW_SHOW );
	return true;
}

LRESULT DXApp::MsgProc( HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam ) {
	switch( msg ) {
	case WM_DESTROY:
		PostQuitMessage( 0 );
		return 0;
	default:
		return DefWindowProc( hwnd, msg, wParam, lParam );
		break;
	}
}

bool DXApp::InitDirect3D( )
{
	UINT createDeviceFlags = 0;

#ifdef _DEBUG
	createDeviceFlags |= D3D11_CREATE_DEVICE_DEBUG;
#endif

	D3D_DRIVER_TYPE driverTypes[] =	{
		D3D_DRIVER_TYPE_HARDWARE,
		D3D_DRIVER_TYPE_WARP,
		D3D_DRIVER_TYPE_REFERENCE
	};
	UINT numDriverTypes = ARRAYSIZE( driverTypes );

	D3D_FEATURE_LEVEL featureLevels[] = {
		D3D_FEATURE_LEVEL_11_0,
		D3D_FEATURE_LEVEL_10_1,
		D3D_FEATURE_LEVEL_10_0,
		D3D_FEATURE_LEVEL_9_3
	};
	UINT numFeatureLevels = ARRAYSIZE( featureLevels );

	DXGI_SWAP_CHAIN_DESC swapDesc;
	ZeroMemory( &swapDesc, sizeof( DXGI_SWAP_CHAIN_DESC ) );
	swapDesc.BufferCount = 1;//double buffered
	swapDesc.BufferDesc.Width = m_ClientWidth;
	swapDesc.BufferDesc.Height = m_ClientHieght;
	swapDesc.BufferDesc.Format = DXGI_FORMAT_R8G8B8A8_UNORM;
	swapDesc.BufferDesc.RefreshRate.Numerator = 60;
	swapDesc.BufferDesc.RefreshRate.Denominator = 1;
	swapDesc.BufferUsage = DXGI_USAGE_RENDER_TARGET_OUTPUT;
	swapDesc.OutputWindow = m_hAppWnd;
	swapDesc.SwapEffect = DXGI_SWAP_EFFECT_DISCARD;
	swapDesc.Windowed = true;
	swapDesc.SampleDesc.Count = 1;
	swapDesc.SampleDesc.Quality = 0;
	swapDesc.Flags = DXGI_SWAP_CHAIN_FLAG_ALLOW_MODE_SWITCH;//alt-enter fullscreen. Doesn't handle buffer.

	HRESULT result;
	for( unsigned int i = 0; i < numDriverTypes; ++i ) {
		result = D3D11CreateDeviceAndSwapChain( NULL, driverTypes[i], NULL, createDeviceFlags,
			featureLevels, numFeatureLevels, D3D11_SDK_VERSION, &swapDesc, &m_pSwapChain, &m_pDevice,
			&m_FeatureLevel, &m_pImmediateContext);
		if( SUCCEEDED( result ) ) {
			m_DriverType = driverTypes[i];
			break;
		}
	}

	if( FAILED ( result ) ) {
		OutputDebugString( _T( "FAILED TO CREATE DEVICE AND SWAP CHAIN" ) );
		return false;
	}
	
	//CREATE RENDER TARGET VIEW
	ID3D11Texture2D* m_pBackBufferTex = 0;
	m_pSwapChain->GetBuffer( NULL, _uuidof( ID3D11Texture2D ), reinterpret_cast<void**>( &m_pBackBufferTex ));
	m_pDevice->CreateRenderTargetView( m_pBackBufferTex, nullptr, &m_pRenderTargetView );
	Memory::SafeRelease( m_pBackBufferTex );

	//BIND RENDER TARGET VIEW
	m_pImmediateContext->OMSetRenderTargets( 1, &m_pRenderTargetView, nullptr );

	//VIEWPORT CREATION
	m_Viewport.Width = static_cast<float>( m_ClientWidth );
	m_Viewport.Height = static_cast<float>( m_ClientHieght );
	m_Viewport.TopLeftX = 0;
	m_Viewport.TopLeftY = 0;
	m_Viewport.MinDepth = 0.0f;
	m_Viewport.MaxDepth = 1.0f;

	//VIEWPORT
	m_pImmediateContext->RSSetViewports( 1, &m_Viewport );

	return true;
}
