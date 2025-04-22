import type { NextPage } from 'next';
import Layout from '../components/Layout/Layout';
import Calculator from '../components/Calculator';

const Home: NextPage = () => {
  return (
    <Layout>
      <div className="flex flex-col items-center">
        <p className="mb-4 text-gray-600">현재 구현된 기능: 덧셈</p>
        <Calculator />
      </div>
    </Layout>
  );
};

export default Home;